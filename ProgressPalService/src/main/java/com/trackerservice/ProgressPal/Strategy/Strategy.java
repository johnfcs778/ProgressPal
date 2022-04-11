package com.trackerservice.ProgressPal.Strategy;

import com.trackerservice.ProgressPal.Requests.Request;

/**
 * Interface for the validate strategy which will be defined at runtime by the context
 */
public interface Strategy {
    public boolean validate(Request request);
}
