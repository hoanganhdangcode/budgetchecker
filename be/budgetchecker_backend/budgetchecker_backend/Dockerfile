# base image for runtime
FROM mcr.microsoft.com/dotnet/aspnet:10.0 AS base
WORKDIR /app
EXPOSE 8080
EXPOSE 8081
#build image
FROM mcr.microsoft.com/dotnet/sdk:10.0 AS build
WORKDIR /src
# Copy csproj vào trong containerfolder budgetchecker_backend/ và restore dependencie
COPY ["budgetchecker_backend/budgetchecker_backend.csproj", "budgetchecker_backend/"]
RUN dotnet restore "budgetchecker_backend/budgetchecker_backend.csproj"
#copy soủce
COPY . .
WORKDIR "/src/budgetchecker_backend"
RUN dotnet build "budgetchecker_backend.csproj" -c Release -o /app/build
#publish
FROM build AS publish
RUN dotnet publish "budgetchecker_backend.csproj" -c Release -o /app/publish /p:UseAppHost=false

#set endpoint
# Stage 4: Final image chạy app
FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "budgetchecker_backend.dll"]