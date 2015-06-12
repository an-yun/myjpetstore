package myjpetstore.persistence;

import myjpetstore.domain.Sequence;

/**
 * Created by zuo on 2015/5/2.
 */
public interface SequenceDAO {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
