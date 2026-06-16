package com.bank.sagaorchestrator.useronboarding.commands;

import com.bank.common.entity.User;
import com.bank.sagaorchestrator.command.BaseCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Command to create a new user in the user service.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateUserCommand extends BaseCommand {
    
    @NotNull
    private User user;
    
    public CreateUserCommand(String commandId, Long sagaId, Instant timestamp, User user) {
        super(commandId, sagaId, timestamp);
        this.user = user;
    }
    
    public static CreateUserCommand create(Long sagaId, User user) {
        return new CreateUserCommand(
            java.util.UUID.randomUUID().toString(),
            sagaId,
            Instant.now(),
            user
        );
    }
}
