package io.github.tuguzt.pcbuilder.backend.spring.repository

import io.github.tuguzt.pcbuilder.backend.spring.model.UserNamePasswordEntity
import io.github.tuguzt.pcbuilder.domain.model.user.UserRole
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userNamePasswordRepository: UserNamePasswordRepository,
    private val passwordEncoder: PasswordEncoder,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val users = listOf(
            UserNamePasswordEntity(
                email = null,
                imageUri = null,
                username = "tuguzT",
                password = passwordEncoder.encode("tugushev_timur"),
                role = UserRole.Administrator,
                // todo
            ),
            UserNamePasswordEntity(
                email = null,
                imageUri = null,
                username = "dr3am_b3ast",
                password = passwordEncoder.encode("tugushev_damir"),
                role = UserRole.Moderator,
            ),
        )
        userNamePasswordRepository.saveAll(users)
    }
}