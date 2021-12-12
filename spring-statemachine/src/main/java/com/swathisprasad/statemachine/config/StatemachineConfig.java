package com.swathisprasad.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachineFactory
public class StatemachineConfig extends StateMachineConfigurerAdapter<StatemachineConfig.TaskState, StatemachineConfig.TaskEvent> {

    @Bean
    public StateMachine<TaskState, TaskEvent> stateMachine(StateMachineListener<TaskState, TaskEvent> listener) throws Exception {
        StateMachineBuilder.Builder<TaskState, TaskEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(TaskState.STARTED)
                .state(TaskState.IN_PROGRESS, null, c -> System.out.println("check status"))
                .state(TaskState.STOPPED, c -> System.out.println("check status"), null);

        builder.configureTransitions()
                .withExternal()
                .source(TaskState.STARTED).target(TaskState.IN_PROGRESS).event(TaskEvent.SEND_NOTIFICATION)
                .action(c -> System.out.println("Send notification"))
                .and()
                .withExternal()
                .source(TaskState.IN_PROGRESS).target(TaskState.STOPPED).event(TaskEvent.SEND_NOTIFICATION);

        StateMachine<TaskState, TaskEvent> stateMachine = builder.build();
        stateMachine.addStateListener(listener);
        return stateMachine;
    }

    @Bean
    public StateMachineListener<TaskState, TaskEvent> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<TaskState, TaskEvent> from, State<TaskState, TaskEvent> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    public enum TaskState {
        IN_PROGRESS, STARTED, STOPPED
    }

    public enum TaskEvent {
        SEND_NOTIFICATION
    }
}
