package iialib.stateSpace.algs.implementation;

import iialib.stateSpace.model.Problem;
import iialib.stateSpace.model.IOperator;
import iialib.stateSpace.model.IState;
import iialib.stateSpace.algs.Solution;

import java.util.Iterator;

import iialib.stateSpace.algs.ASearchAlgorithmStats;

public class DepthFirstSearchNaiveStats<S extends IState<O>, O extends IOperator<S>> 
			extends ASearchAlgorithmStats <S, O> {

	private static final String  DESCRIPTION = "Naive Depth-First search";
	private Problem<S> problem;
	
	public DepthFirstSearchNaiveStats() {
		//TODO
	}

	@Override
	public Solution<S, O> solve(Problem<S> problem) {
		this.problem = problem;
		Solution<S,O> solution = dfs(problem.getInitialState());
		return solution;
	}
	
	private Solution<S,O> dfs(S state){
		if(problem.isTerminal(state)) {
			return new Solution<S,O>(state,null,null);
		} else {
			Solution<S,O> sol;
			Iterator<O> it = state.applicableOperators();
			O operator;
			while(it.hasNext()) {
				operator = it.next();
				sol = dfs(operator.successor(state));
				return new Solution<S,O>(state, operator, sol);
			}
			return new Solution<S,O>(state,null,null);
		}
	}

}
