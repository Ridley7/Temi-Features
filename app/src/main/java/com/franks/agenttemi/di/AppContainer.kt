
import com.franks.agenttemi.data.datasource.MockEnvironmentDataSource
import com.franks.agenttemi.data.repository.EnvironmentRepositoryImplementation
import com.robotemi.sdk.Robot
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val aiModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single <OpenAIService> { get<Retrofit>().create(OpenAIService::class.java)  }

    single <OpenIADataSource> { OpenIADataSourceImplementation ( openAIService = get()) }

    single <AIRepository> { AIRepositoryImplementation ( remoteDataSource = get()) }
}

val attentionModule = module {
    single { AttentionManager(get()) }
}

val avatarStateModule = module {
    single { AvatarStateManager() }
}

val voiceModule = module {
    single <VoiceManager> { AndroidVoiceManager (get())}
}

val speechModule = module {
    single { SpeechManager(get(), get())}
}


val environmentModule = module {

    //DataSource
    single <EnvironmentDataSource> {MockEnvironmentDataSource()}

    //Repository
    single <EnvironmentRepository> { EnvironmentRepositoryImplementation(get()) }

    //UseCases
    factory { ObserveEnvironmentUseCase(get()) }
    factory { GetEnvironmentRecommendationUseCase( aiRepository = get()) }

    //ViewModel
    viewModel{
        EnvironmentViewModel(
            observeEnvironmentUseCase = get(),
            recommendationUseCase = get(),
            attentionManager = get(),
            avatarManager = get()
        )
    }
}
