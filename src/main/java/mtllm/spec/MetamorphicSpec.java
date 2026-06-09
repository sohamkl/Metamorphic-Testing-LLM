package mtllm.spec;

/**
 * Contract for a developer-owned metamorphic relation (Mode 4).
 *
 * <p>I is the SUT input type; O is the SUT output type. The developer implements this
 * once per SUT and the framework (or Randoop) only generates source inputs of type I --
 * it never touches the relation logic.</p>
 */
public interface MetamorphicSpec<I, O> {
    /** Produce the follow-up input from a source input according to the MR. */
    I generateFollowUp(I source);

    /** Assert that the MR output relation holds between source and follow-up outputs. */
    void assertRelation(O sourceOutput, O followUpOutput);
}
