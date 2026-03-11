using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace budgetchecker_backend.Domain.Entities
{
    public class User
    {
        public Guid Id { get; private set; }

        public string Name { get; private set; }="";

        public string Email { get; private set; }

        public string PasswordHash { get; private set; }

        public UserRole Role { get; private set; } = UserRole.User;

        public UserStatus Status { get; private set; } = UserStatus.Inactive;

        public DateTime CreatedAt { get; private set; }

        public DateTime UpdatedAt { get; private set; }

        public int TimeZoneCode { get; private set; } = 0;

        public ICollection<Transaction> Transactions { get; private set; } = new List<Transaction>();

        public User(string name, string email, string passwordHash)
        {
            Id = Guid.NewGuid();
            Name = name;
            Email = email;
            PasswordHash = passwordHash;
            CreatedAt = DateTime.UtcNow;
            UpdatedAt = DateTime.UtcNow;
        }

        public void UpdatePassword(string newPasswordHash)
        {
            PasswordHash = newPasswordHash;
            UpdatedAt = DateTime.UtcNow;
        }

        public void Activate()
        {
            Status = UserStatus.Active;
            UpdatedAt = DateTime.UtcNow;

        }
        public void Ban()
        {
            Status = UserStatus.Ban;
            UpdatedAt = DateTime.UtcNow;
        }

        public void UpdateTimeZone(int tz)
        {
            TimeZoneCode = tz;
            UpdatedAt = DateTime.UtcNow;
        }
    }
    public enum UserStatus
    {
        Inactive = 0,
        Active = 1,
        Ban = -1,
        Deleted = -2
    }
    public enum UserRole
    {
        User = 0,
        Admin = 1
    }
}