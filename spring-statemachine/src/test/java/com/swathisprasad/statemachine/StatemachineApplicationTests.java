package com.swathisprasad.statemachine;

import com.swathisprasad.statemachine.config.StatemachineConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatemachineConfig.class)
class StatemachineApplicationTests {

	@Autowired
	private StateMachine<StatemachineConfig.TaskState, StatemachineConfig.TaskEvent> stateMachine;

	@BeforeEach
	public void setUp() {
		stateMachine.start();
	}

	@Test
	public void whenSimpleStringStateMachineEvents_thenEndState() {
		assertEquals(StatemachineConfig.TaskState.STARTED, stateMachine.getState().getId());

		stateMachine.sendEvent(StatemachineConfig.TaskEvent.SEND_NOTIFICATION);
		assertEquals(StatemachineConfig.TaskState.IN_PROGRESS, stateMachine.getState().getId());
	}

	@AfterEach
	public void tearDown() {
		stateMachine.stop();
	}
}
