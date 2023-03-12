package dev.johnoreilly.confetti.di

import com.apollographql.apollo3.cache.normalized.FetchPolicy
import dev.johnoreilly.confetti.ConferenceRefresh
import dev.johnoreilly.confetti.ConfettiViewModel
import dev.johnoreilly.confetti.sessiondetails.SessionDetailsViewModel
import dev.johnoreilly.confetti.speakerdetails.SpeakerDetailsViewModel
import dev.johnoreilly.confetti.work.WorkManagerConferenceRefresh
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        ConfettiViewModel(get(), get(), get())
    }
    viewModel { SessionDetailsViewModel(get(), get()) }
    viewModel { SpeakerDetailsViewModel(get(), get()) }
    single<ConferenceRefresh> { WorkManagerConferenceRefresh(get()) }
    single {
        // Assume an online first strategy for Mobile
        // But use Cache for initial results
        FetchPolicy.CacheAndNetwork
    }
}
