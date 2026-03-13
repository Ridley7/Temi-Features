
import com.franks.agenttemi.data.datasource.MockEnvironmentDataSource
import com.franks.agenttemi.data.repository.EnvironmentRepositoryImplementation
import com.robotemi.sdk.Robot
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


val voiceModule = module {
    /*
    single { Robot.getInstance()}
    single<VoiceManager> {TemiVoiceManager(get())}
     */

    single <VoiceManager> { AndroidVoiceManager (get())}
}

val speechModule = module {
    single { SpeechManager(get())}
}


val environmentModule = module {

    //DataSource
    single <EnvironmentDataSource> {MockEnvironmentDataSource()}

    //Repository
    single <EnvironmentRepository> { EnvironmentRepositoryImplementation(get()) }

    //UseCases
    factory { ObserveEnvironmentUseCase(get()) }
    factory { GetEnvironmentRecommendationUseCase() }

    //ViewModel
    viewModel{
        EnvironmentViewModel(
            observeEnvironmentUseCase = get(),
            recommendationUseCase = get(),
            speechManager = get()
        )
    }
}
