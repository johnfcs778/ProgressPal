package com.trackerservice.ProgressPal.Strategy;

import com.trackerservice.ProgressPal.Requests.Request;

/**
 * Context object for implementing the validation via the strategy pattern
 * Takes in a strategy and sets it for use in the execute function
 */
public class Context {
    private Strategy mStrategy;

    public Context(Strategy strategy) {
        this.mStrategy = strategy;
    }

    public boolean execute(Request req) {
        return mStrategy.validate(req);
    }
}
