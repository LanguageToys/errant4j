package edu.guym.errantj.lang.en.classify.rules;

import edu.guym.aligner.edit.Edit;
import edu.guym.errantj.core.grammar.GrammaticalError;
import edu.guym.errantj.lang.en.classify.CategoryMatchRule;
import edu.guym.errantj.lang.en.classify.rules.common.Predicates;
import edu.guym.spacyj.api.containers.Token;
import edu.guym.spacyj.api.features.Pos;
import edu.guym.spacyj.api.features.Tag;

import java.util.function.Predicate;

/**
 * Verb form errors involve corrections between members of the set of bare infinitive, to-
 * infinitive, gerund and participle forms; e.g. {eat, to eat, eating, eaten}.
 * Since infinitives tend to have exactly the same form as non-3rd-person present tense forms however
 * (cf. ‘I want to eat cake’ versus ‘I eat cake’), we must use fine-grained POS tags to differentiate between them.
 * <p>
 * Other types of verb form errors involve infinitival to.
 * The next rule hence captures missing or unnecessary to particles that are not prepositions:
 * <p>
 * 1. There is only one token on one side of the edit, and
 * 2. That token is to, and
 * 3. That token is POS tagged as PART, and
 * 4. That token is not parsed as prep.
 */
public class MissingOrUnnecessaryVerbFormInfinitivalToRule extends CategoryMatchRule {

    @Override
    public GrammaticalError.Category getCategory() {
        return GrammaticalError.Category.VERB_FORM;
    }

    @Override
    public boolean isSatisfied(Edit<Token> edit) {
        return edit
                .filter(onlyOneTokenOnOneSide())
                .filter(tokenIsTo())
                .filter(tokenIsPART())
                .filter(tokenIsNotPrep())
                .isPresent();
    }

    public Predicate<Edit<Token>> onlyOneTokenOnOneSide() {
        return edit -> edit.matches(Predicates.ofSize(1, 0)) || edit.matches(Predicates.ofSize(0, 1));
    }

    public Predicate<Edit<Token>> tokenIsTo() {
        return edit -> edit
                .stream()
                .findFirst()
                .filter(token -> token.lower().equals("to"))
                .isPresent();
    }

    public Predicate<Edit<Token>> tokenIsPART() {
        return edit -> edit
                .stream()
                .findFirst()
                .filter(token -> Pos.PART.matches(token.pos()))
                .isPresent();
    }

    public Predicate<Edit<Token>> tokenIsNotPrep() {
        return edit -> edit
                .stream()
                .findFirst()
                .filter(token -> Tag.IN.matches(token.tag()))
                .isEmpty();
    }

}
