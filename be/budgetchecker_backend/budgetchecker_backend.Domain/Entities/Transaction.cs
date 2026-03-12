namespace budgetchecker_backend.Domain.Entities
{
    public class Transaction
    {
        public Guid Id { get; private set; }

        public Guid UserId { get; private set; }

        public TransactionType Type { get; private set; }

        public TransactionCategory Category { get; private set; }

        public string? Description { get; private set; }

        public decimal Amount { get; private set; }

        public DateTime TransactionDate { get; private set; }

        public DateTime CreatedAt { get; private set; }

        public DateTime UpdatedAt { get; private set; }

        // Navigation property
        public User? User { get; private set; }


        public Transaction(Guid userId, TransactionType type, TransactionCategory category, string description, decimal amount, DateTime transactionDate)
        {
            Id = Guid.NewGuid();
            UserId = userId;
            Type = type;
            Category = category;
            Description = description;
            Amount = amount;
            TransactionDate = transactionDate;
            CreatedAt = DateTime.UtcNow;
            UpdatedAt = DateTime.UtcNow;
        }

        public void Update(TransactionCategory category, string description, decimal amount, DateTime transactionDate)
        {
            Category = category;
            Description = description;
            Amount = amount;
            TransactionDate = transactionDate;
            UpdatedAt = DateTime.UtcNow;
        }
    }

    public enum TransactionType
    {
        Income = 1,
        Expense = -1
    }

    public enum TransactionCategory
    {
        // Income
        Salary = 100,
        Freelance = 101,
        Investment = 102,
        OtherIncome = 103,

        // Expense
        Food = 200,
        Housing = 201,
        Transport = 202,
        Health = 203,
        Shopping = 204,
        Entertainment = 205,
        Education = 206,
        OtherExpense = 207
    }
}       
// transaction.cs