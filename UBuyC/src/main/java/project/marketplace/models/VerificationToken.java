package project.marketplace.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {
    private static final int EXPIRATION = 1; // 24 hours

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private int otp;

    private LocalDate expiryDate;

    public VerificationToken(User user) {
        this.user = user;
        this.otp = new Random().nextInt(900000) + 100000; // random 6-digit int
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public User getUser() {
        return this.user;
    }

    public int getOtp() {
        return this.otp;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }
    
    private LocalDate calculateExpiryDate(int expiryTimeInMinutes) {
        return LocalDate.now().plusDays(expiryTimeInMinutes);
    }
}
