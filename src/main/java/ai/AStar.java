package ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {
  private static int DEFAULT_HV_COST = 1; // Horizontal - Vertical Cost
  private int hvCost;
  private Node[][] searchArea;
  private PriorityQueue<Node> openList;
  private Set<Node> closedSet;
  private Node initialNode;
  private Node finalNode;

  public AStar(int rows, int cols, Node initialNode, Node finalNode, int hvCost) {
    this.hvCost = hvCost;
    setInitialNode(initialNode);
    setFinalNode(finalNode);
    this.searchArea = new Node[rows][cols];
    this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
      @Override
      public int compare(Node node0, Node node1) {
        return Integer.compare(node0.getF(), node1.getF());
      }
    });
    setNodes();
    this.closedSet = new HashSet<>();
  }

  public AStar(int rows, int cols, Node initialNode, Node finalNode) {
    this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST);
  }

  private void setNodes() {
    for (int i = 0; i < searchArea.length; i++) {
      for (int j = 0; j < searchArea[0].length; j++) {
        Node node = new Node(i, j);
        node.calculateHeuristic(getFinalNode());
        this.searchArea[i][j] = node;
      }
    }
  }

  public void setBlocks(int[][] blocksArray) {
    for (int i = 0; i < blocksArray.length; i++) {
      int row = blocksArray[i][0];
      int col = blocksArray[i][1];
      setBlock(row, col);
    }
  }

  public List<Node> findPath() {
    openList.add(initialNode);
    while (!isEmpty(openList)) {
      Node currentNode = openList.poll();
      closedSet.add(currentNode);
      if (isFinalNode(currentNode)) {
        return getPath(currentNode);
      } else {
        addAdjacentNodes(currentNode);
      }
    }
    return new ArrayList<Node>();
  }

  private List<Node> getPath(Node currentNode) {
    List<Node> path = new ArrayList<Node>();
    path.add(currentNode);
    Node parent;
    while ((parent = currentNode.getParent()) != null) {
      path.add(0, parent);
      currentNode = parent;
    }
    return path;
  }

  private void addAdjacentNodes(Node currentNode) {
    addAdjacentUpperRow(currentNode);
    addAdjacentMiddleRow(currentNode);
    addAdjacentLowerRow(currentNode);
  }

  private void addAdjacentLowerRow(Node currentNode) {
    int row = currentNode.getRow();
    int col = currentNode.getCol();
    int lowerRow = row + 1;
    if (lowerRow < getSearchArea().length) {
      checkNode(currentNode, col, lowerRow, getHvCost());
    }
  }

  private void addAdjacentMiddleRow(Node currentNode) {
    int row = currentNode.getRow();
    int col = currentNode.getCol();
    int middleRow = row;
    if (col - 1 >= 0) {
      checkNode(currentNode, col - 1, middleRow, getHvCost());
    }
    if (col + 1 < getSearchArea()[0].length) {
      checkNode(currentNode, col + 1, middleRow, getHvCost());
    }
  }

  private void addAdjacentUpperRow(Node currentNode) {
    int row = currentNode.getRow();
    int col = currentNode.getCol();
    int upperRow = row - 1;
    if (upperRow >= 0) {
      checkNode(currentNode, col, upperRow, getHvCost());
    }
  }

  private void checkNode(Node currentNode, int col, int row, int cost) {
    Node adjacentNode = getSearchArea()[row][col];
    if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
      if (!getOpenList().contains(adjacentNode)) {
        if (adjacentNode.isBrick()) {
          adjacentNode.setNodeData(currentNode, 30);
        } else {
          adjacentNode.setNodeData(currentNode, cost);
        }
        getOpenList().add(adjacentNode);
      } else {
        boolean changed;
        if (adjacentNode.isBrick()) {
          changed = adjacentNode.checkBetterPath(currentNode, 30);
        } else {
          changed = adjacentNode.checkBetterPath(currentNode, cost);
        }
        if (changed) {
          // Remove and Add the changed node, so that the PriorityQueue can sort again its
          // contents with the modified "finalCost" value of the modified node
          getOpenList().remove(adjacentNode);
          getOpenList().add(adjacentNode);
        }
      }
    }
  }

  private boolean isFinalNode(Node currentNode) {
    return currentNode.equals(finalNode);
  }

  private boolean isEmpty(PriorityQueue<Node> openList) {
    return openList.size() == 0;
  }

  public void setBlock(int row, int col) {
    this.searchArea[row][col].setBlock(true);
  }

  public void setBrick(int row, int col) {
    this.searchArea[row][col].setBrick(true);
  }

  public Node getInitialNode() {
    return initialNode;
  }

  public void setInitialNode(Node initialNode) {
    this.initialNode = initialNode;
  }

  public Node getFinalNode() {
    return finalNode;
  }

  public void setFinalNode(Node finalNode) {
    this.finalNode = finalNode;
  }

  public Node[][] getSearchArea() {
    return searchArea;
  }

  public void setSearchArea(Node[][] searchArea) {
    this.searchArea = searchArea;
  }

  public PriorityQueue<Node> getOpenList() {
    return openList;
  }

  public void setOpenList(PriorityQueue<Node> openList) {
    this.openList = openList;
  }

  public Set<Node> getClosedSet() {
    return closedSet;
  }

  public void setClosedSet(Set<Node> closedSet) {
    this.closedSet = closedSet;
  }

  public int getHvCost() {
    return hvCost;
  }

  public void setHvCost(int hvCost) {
    this.hvCost = hvCost;
  }
}

