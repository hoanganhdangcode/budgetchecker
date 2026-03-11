# Runtime image
FROM mcr.microsoft.com/dotnet/aspnet:10.0 AS base
WORKDIR /app
EXPOSE 8080

# Build image
FROM mcr.microsoft.com/dotnet/sdk:10.0 AS build
WORKDIR /src

# copy project files để restore dependency
COPY ["budgetchecker_backend/budgetchecker_backend.csproj", "budgetchecker_backend/"]
COPY ["budgetchecker_backend.Application/budgetchecker_backend.Application.csproj", "budgetchecker_backend.Application/"]
COPY ["budgetchecker_backend.Infrastructure/budgetchecker_backend.Infrastructure.csproj", "budgetchecker_backend.Infrastructure/"]

RUN dotnet restore "budgetchecker_backend/budgetchecker_backend.csproj"

# copy toàn bộ source
COPY . .

WORKDIR "/src/budgetchecker_backend"

RUN dotnet build "budgetchecker_backend.csproj" -c Release -o /app/build

# publish
FROM build AS publish
RUN dotnet publish "budgetchecker_backend.csproj" -c Release -o /app/publish /p:UseAppHost=false

# final runtime container
FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "budgetchecker_backend.dll"]