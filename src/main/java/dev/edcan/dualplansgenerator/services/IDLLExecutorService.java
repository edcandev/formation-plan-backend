package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;

public interface IDLLExecutorService {

    void generatePlan(PlanGeneratorRequest planGeneratorRequest);
}
