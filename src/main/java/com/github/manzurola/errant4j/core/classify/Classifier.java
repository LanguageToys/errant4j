package com.github.manzurola.errant4j.core.classify;

import com.github.manzurola.aligner.edit.Edit;
import com.github.manzurola.errant4j.core.errors.GrammaticalError;
import com.github.manzurola.spacy4j.api.containers.Token;


public interface Classifier {

    /**
     * Get the {@link GrammaticalError} for the supplied Edit.
     */
    GrammaticalError classify(Edit<Token> edit);

}
