package cis3723.okbu.edu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeNode<S, M> {
    public ArrayList<TreeNode<S, M>> children;
    public Interface<S, M> gamestate;
    public S state;
    public TreeNode<S, M> parent;
    public TreeNode(ArrayList<TreeNode<S, M>> children, S state, Interface<S, M> gamestate, TreeNode<S, M> parent) {
        this.children = children;
        this.state = state;
        this.gamestate = gamestate;
        this.parent = parent;
    }
    public ArrayList<TreeNode<S, M>> getcChildren() {
        return children;
    }
    public void setChildren(ArrayList<TreeNode<S, M>> children) {
        this.children = children;
    }
    public Interface<S, M> getGamestate() {
        return gamestate;
    }
    public void setGamestate(Interface<S, M> gamestate) {
        this.gamestate = gamestate;
    }
    public S getState() {
        return state;
    }
    public void setState(S state) {
        this.state = state;
    }
    public TreeNode<S, M> getParent() {
        return parent;
    }
    public void setParent(TreeNode<S, M> parent) {
        this.parent = parent;
    }

    public int count_nodes(S state, int depth) {
        if (state == null || depth <= 0 || gamestate.is_terminal(state)) {
            return 0;
        }

        int count = 0;
        for (M move : gamestate.get_legal_moves(state)) {
            S childState = gamestate.move(state, move);
            if (childState != null) {
                count++;
                count += count_nodes(childState, depth - 1);
            }
        }
        return count;
    }

    public void BFS(S state) {
        if (state == null || gamestate == null) {
            return;
        }

        ArrayList<TreeNode<S, M>> currentLevel = new ArrayList<>();
        currentLevel.add(new TreeNode<>(new ArrayList<>(), state, gamestate, null));

        for (int depth = 0; depth < 2; depth++) {
            ArrayList<TreeNode<S, M>> nextLevel = new ArrayList<>();
            for (TreeNode<S, M> node : currentLevel) {
                nextLevel.addAll(node.generate_children(node.state));
            }
            currentLevel = nextLevel;
        }

        Set<S> printedStates = new HashSet<>();
        for (TreeNode<S, M> node : currentLevel) {
            if (printedStates.add(node.state)) {
                gamestate.display(node.state);
            }
        }
    }

    public boolean DFS(Interface<S,M> gamestate){
        if (state == null) {
            return false;
        }
        if (gamestate.is_terminal(state)) {
            gamestate.display(state);
            return true;
        }

        for (TreeNode<S, M> child : generate_children(state)) {
            if (child.DFS(gamestate)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<TreeNode<S, M>> generate_children(S state) {
        ArrayList<TreeNode<S, M>> generated = new ArrayList<TreeNode<S, M>>();
        if (state == null || gamestate.is_terminal(state)) {
            children = generated;
            return generated;
        }

        ArrayList<M> legalMoves = gamestate.get_legal_moves(state);
        Set<S> seenStates = new HashSet<S>();
        for (M move : legalMoves) {
            S childState = gamestate.move(state, move);
            if (childState != null && seenStates.add(childState)) {
                generated.add(new TreeNode<>(
                    new ArrayList<>(), childState, gamestate, this));
            }
        }
        children = generated;
        return generated;
    }
}
