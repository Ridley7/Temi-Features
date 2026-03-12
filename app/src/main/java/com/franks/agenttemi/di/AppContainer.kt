
import com.franks.agenttemi.data.datasource.MockEnvironmentDataSource
import com.franks.agenttemi.data.repository.EnvironmentRepositoryImplementation
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

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
            get(),
            get()
        )
    }
}
