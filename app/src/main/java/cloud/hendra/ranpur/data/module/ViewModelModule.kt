package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.ui.viewmodel.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel {
        AuthViewModel(
            loginUseCase = get(),
            userUseCase = get(),
            tokenManager = get(),
        )
    }
}