class Node {
    Node left, right;
    int data;

    Node() {
    }

    Node(int data) {
        this.data = data;
    }
}

// pakai konstan dengan enum hindari "error", daripada tipo
enum Trasversal {
    PREORDER, INORDER, POSTORDER,
}

public class Tree {
    private Node root;
    private StringBuilder datas;
    private Trasversal mode;

    Tree() {
        this.datas = new StringBuilder();
        // default nya inoder
        this.mode = Trasversal.INORDER;
    }

    Tree(Trasversal mode) {
        this.mode = mode;
    }

    public void setMode(Trasversal mode) {
        this.mode = mode;
    }

    public boolean contains(int data) {
        Node current = root;
        while (current != null) {
            if (current.data == data)
                return true;
            if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void add(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parrent;
            while (current != null) {
                parrent = current;
                if (data < current.data) {
                    current = current.left;
                    if (current == null) {
                        parrent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parrent.right = node;
                        return;
                    }
                }

            }
        }
    }

    public void remove(int data) {
        remove(root, data);
    }

    Node remove(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = remove(root.left, data);
        }
        else if (data > root.data) {
            root.right = remove(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = remove(root.right, root.data);
        }

        return root;
    }

    int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public void preorder(Node current) {
        if (current != null) {
            datas.append(current.data);
            datas.append(", ");
            preorder(current.left);
            preorder(current.right);
        }
    }

    public void inoder(Node current) {
        if (current != null) {
            inoder(current.left);
            datas.append(current.data);
            datas.append(", ");
            inoder(current.right);
        }
    }

    public void postorder(Node current) {
        if (current != null) {
            postorder(current.left);
            postorder(current.right);
            datas.append(current.data);
            datas.append(", ");
        }
    }

    @Override
    public String toString() {
        datas.setLength(0); // biar tak numpuk numpuk, biar enak di test
        switch (mode) {
            case PREORDER:
                preorder(this.root);
                break;
            case POSTORDER:
                postorder(this.root);
                break;
            default:
                inoder(this.root);
                break;
        }
        datas.delete(datas.length() - 2, datas.length()); // hapus char ' ' dan ','
        datas.insert(0, "[");
        datas.insert(datas.length(), "]");
        return datas.toString();
    }
}