using RabbitMQ.Client;
using System.Text;

public class RabbitMqService
{
    private readonly IConfiguration _config;

    public RabbitMqService(IConfiguration config)
    {
        _config = config;
    }

    public async Task PublishAsync(string message)
    {
        var factory = new ConnectionFactory
        {
            HostName = _config["RabbitMQ:Host"],
            Port = int.Parse(_config["RabbitMQ:Port"]),
            UserName = _config["RabbitMQ:Username"],
            Password = _config["RabbitMQ:Password"]
        };

        await using var connection = await factory.CreateConnectionAsync();
        await using var channel = await connection.CreateChannelAsync();

        await channel.QueueDeclareAsync(
            queue: "test_queue",
            durable: true,
            exclusive: false,
            autoDelete: false,
            arguments: null
        );

        var body = Encoding.UTF8.GetBytes(message);

        await channel.BasicPublishAsync(
            exchange: "",
            routingKey: "test_queue",
            body: body
        );
    }
}