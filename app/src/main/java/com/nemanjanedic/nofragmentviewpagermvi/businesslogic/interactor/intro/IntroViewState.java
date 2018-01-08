package com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro;

public interface IntroViewState {

    final class LoadingFirstPage implements IntroViewState {
        @Override
        public String toString() {
            return "LoadingFirstPage{}";
        }
    }

    final class LoadedFirstPage implements IntroViewState {
        private final String greeting;

        public LoadedFirstPage(String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }

        @Override
        public String toString() {
            return "LoadedFirstPage{" + "greeting='" + greeting + '\'' + '}';
        }
    }

    final class LoadingSecondPage implements IntroViewState {
        @Override
        public String toString() {
            return "LoadingSecondPage{}";
        }
    }

    final class LoadedSecondPage implements IntroViewState {
        private final String greeting;

        public LoadedSecondPage(String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }

        @Override
        public String toString() {
            return "LoadedSecondPage{" + "greeting='" + greeting + '\'' + '}';
        }
    }

    final class ThirdPage implements IntroViewState {
        @Override
        public String toString() {
            return "ThirdPage{}";
        }
    }

    final class Continue implements IntroViewState {
        @Override
        public String toString() {
            return "Continue{}";
        }
    }

}
