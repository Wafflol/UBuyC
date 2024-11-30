package project.marketplace.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * The basic verification token model. It is annotated with @Entity as it is the VerificationToken class that 
 * will be used as a DTO (data-transfer object). 
 */
@Entity
public class VerificationToken {
    private static final int EXPIRATION = 10; // 24 hours

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private final User user;

    private final int otp;

    private final LocalDateTime expiryDate;

    /**
     * Creates a new verification token for the given user
     * 
     * @param user the user that the token is being created for
     */
    public VerificationToken(User user) {
        this.user = user;
        this.otp = new Random().nextInt(900000) + 100000; // random 6-digit int
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    /**
     * Retrieves the user associated with this OTP.
     * 
     * @return The user object representing the user linked to this OTP.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Retrieves the OTP value.
     * 
     * @return The OTP code as an {@code int}.
     */
    public int getOtp() {
        return this.otp;
    }

    /**
     * Retrieves the expiry date of the OTP.
     * 
     * @return The expiry date of the OTP as a {@link LocalDate}.
     */
    public LocalDateTime getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * Calculates the expiry date based on the provided expiry time in minutes.
     * 
     * @param expiryTimeInMinutes The number of minutes after which the OTP expires.
     * @return A {@link LocalDate} representing the calculated expiry date.
     */
    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
    }
}
