using budgetchecker_backend.services;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi;
using Microsoft.OpenApi.Models;
using budgetchecker_backend.Infrastructure.Data;


var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi

var service = builder.Services;
//swagger
service.AddEndpointsApiExplorer();
service.AddSwaggerGen(
    option => {
        option.SwaggerDoc("v1", new() { Title = "Budget Checker", Version = "v1", Description = "Backend Budget Checker api swagger document" });
        option.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
        {
            Name = "Authorization",
            Type = SecuritySchemeType.Http,
            Scheme = "bearer",
            BearerFormat = "JWT",
            In = ParameterLocation.Header,
            Description = "Nhập JWT Token theo định dạng: **Bearer {token}**"
        });
        option.AddSecurityRequirement(new OpenApiSecurityRequirement
                    {
                        {
                            new OpenApiSecurityScheme
                            {
                                Reference = new OpenApiReference {
                                    Type = ReferenceType.SecurityScheme,
                                    Id = "Bearer" }
                            }, new List<string>() }
                    });
    });
service.AddSingleton<RabbitMqService>();

var connectionString =
    builder.Configuration.GetConnectionString("DefaultConnection");

builder.Services.AddDbContext<AppDbContext>(options =>
{
    options.UseMySql(
        connectionString,
        ServerVersion.AutoDetect(connectionString)
    );
});

var app = builder.Build();
app.UseSwagger();
app.UseSwaggerUI(c =>
{
    c.SwaggerEndpoint("/swagger/v1/swagger.json", "bugetchecker v1");
    c.RoutePrefix = string.Empty;
});



//app.UseHttpsRedirection();

var summaries = new[]
{
    "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
};

app.MapGet("/weatherforecast", () =>
{
    var forecast = Enumerable.Range(1, 5).Select(index =>
        new WeatherForecast
        (
            DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
            Random.Shared.Next(-20, 55),
            summaries[Random.Shared.Next(summaries.Length)]
        ))
        .ToArray();
    return forecast;
})
.WithName("GetWeatherForecast");

app.MapGet("/ping", () =>
{
    return "Pong";
});
app.MapPost("/test-rabbit", (RabbitMqService rabbit) =>
{
    var message = $"Hello RabbitMQ {DateTime.UtcNow}";

    rabbit.Publish(message);

    return Results.Ok(new
    {
        status = "sent",
        message = message
    });
});


app.Run();

internal record WeatherForecast(DateOnly Date, int TemperatureC, string? Summary)
{
    public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);
}
