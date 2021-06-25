package edu.guym.errantj.lang.en.classify;

import edu.guym.aligner.edit.Edit;
import edu.guym.errantj.core.grammar.GrammaticalError;
import edu.guym.spacyj.api.containers.Token;

public abstract class CategoryMatchRule implements Rule {

    @Override
    public GrammaticalError classify(Edit<Token> edit) {
        return isSatisfied(edit) ? GrammaticalError.of(edit, getCategory()) : GrammaticalError.unknown(edit);
    }

    public abstract GrammaticalError.Category getCategory();

    public abstract boolean isSatisfied(Edit<Token> edit);
}
