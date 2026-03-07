using RabbitMQ.Client;
using System.Text;

namespace budgetchecker_backend.services
{
  
    public class RabbitMqService
    {
        private readonly IConfiguration _config;

        public RabbitMqService(IConfiguration config)
        {
            _config = config;
        }

        public void Publish(string message)
        {
            var factory = new ConnectionFactory()
            {
                HostName = _config["RabbitMQ:Host"],
                Port = int.Parse(_config["RabbitMQ:Port"]),
                UserName = _config["RabbitMQ:Username"],
                Password = _config["RabbitMQ:Password"]
            };

            using var connection = factory.CreateConnection();
            using var channel = connection.CreateModel();

            channel.QueueDeclare(
                queue: "test_queue",
                durable: true,
                exclusive: false,
                autoDelete: false,
                arguments: null
            );

            var body = Encoding.UTF8.GetBytes(message);

            channel.BasicPublish(
                exchange: "",
                routingKey: "test_queue",
                basicProperties: null,
                body: body
            );
        }
    }
}
