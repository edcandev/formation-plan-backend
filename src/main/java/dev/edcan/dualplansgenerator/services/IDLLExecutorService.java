package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;

public interface IDLLExecutorService {

    boolean generatePlan(PlanGeneratorRequest planGeneratorRequest);
}
