package de.atlasmc.inventory.component;

import java.util.List;

import de.atlasmc.block.blockpredicate.BlockPredicate;

public interface AbstractBlockPredicateComponent extends AbstractTooltipComponent {
	
	List<BlockPredicate> getPredicates();
	
	boolean hasPredicates();
	
	void addPredicate(BlockPredicate predicate);
	
	void removePredicate(BlockPredicate predicate);
	
	AbstractBlockPredicateComponent clone();

}
