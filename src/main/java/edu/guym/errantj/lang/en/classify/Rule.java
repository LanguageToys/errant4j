package edu.guym.errantj.lang.en.classify;

import edu.guym.aligner.edit.Edit;
import edu.guym.errantj.core.grammar.GrammaticalError;
import edu.guym.spacyj.api.containers.Token;

public interface Rule {

    GrammaticalError classify(Edit<Token> edit);
}
