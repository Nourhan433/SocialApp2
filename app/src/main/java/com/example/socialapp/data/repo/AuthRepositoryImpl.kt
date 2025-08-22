import com.example.socialapp.core.sha256
import com.example.socialapp.data.local.UserDao
import com.example.socialapp.data.local.UserEntity
import com.example.socialapp.data.repo.AuthError
import com.example.socialapp.data.repo.AuthRepository
import com.example.socialapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {

    // null mean right now no logged in user
    override val currentUserFlow: Flow<User?> = flowOf(null)

    override suspend fun signUp(username: String, email: String, password: String): User {
        val existing = userDao.getByEmail(email)
        if (existing != null) throw AuthError.EmailAlreadyUsed as Throwable
        else {
            val entity = UserEntity(
                email = email.trim(),
                username = username.trim(),
                passwordHash = sha256(password)
            )

            val newId = userDao.insert(entity)
            //Da hy3ml map mn aldatabase lel user Domain
            return entity.copy(id = newId).toDomain()
        }
    }

    override suspend fun signIn(email: String, password: String): User {
        val user = userDao.getByEmail(email.trim())
        val ok = user != null && user.passwordHash == sha256(password)
        if (!ok) throw AuthError.InvalidCredentials
        // covert entity to model that app will use
        return user!!.toDomain()
    }

    override suspend fun signOut() {
        // no session to clear
    }
}
// to domain h t map aldatbase lel model 34an nfsl allogic 3n
//al ui m4 3aizen al ui y3ml access leldatabase direct
//1- de al2wl w b3den aly fo2
private fun UserEntity.toDomain() = User(
    id = id,
    email = email,
    username = username
)
