package ru.knowledge_base_rest.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.knowledge_base_rest.entities.security.SecurityUser;
import ru.knowledge_base_rest.entities.user.AppUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormDTO {

    private SecurityUser securityUser;
    private AppUser squadUser;
}
