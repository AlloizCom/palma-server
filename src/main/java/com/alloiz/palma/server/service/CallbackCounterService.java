package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.CallbackCounter;

public interface CallbackCounterService {

    CallbackCounter getCounter(Long id);

    CallbackCounter update(CallbackCounter counter);

    Boolean delete(Long id);

    Boolean createDefaultCounter();

    CallbackCounter resetCounter(Long id);

    CallbackCounter incrementCounter(Long id);
}
