public class HuffmanTree {
    private PriorityQueue<HuffmanTreeNode> queue;
    int size;
    public HuffmanTree(PriorityQueue<HuffmanTreeNode> queue) {
        this.queue = queue;
        size = queue.size();
        buildTree();

    }

    private void buildTree() {
        while (queue.size() > 1) {
            HuffmanTreeNode newNode = new HuffmanTreeNode();
            newNode.left = queue.removeNext();
            newNode.right= queue.removeNext();
            newNode.setValue(newNode.left.getValue() + newNode.right.getValue());
            queue.addElement(newNode);
        }
    }

    public void fillTable(HuffmanFrequencyTable table) {
        fillTableAux((HuffmanTreeNode) queue.peek(), "", table);
    }

    private void fillTableAux(HuffmanTreeNode current, String code, HuffmanFrequencyTable table) {
        if(current == null) return;
        Character key = current.getKey();
        if(key != null) {
            table.setCode(key, code);
        }
        fillTableAux(current.left, code + "0", table);
        fillTableAux(current.right, code + "1", table);
    }

    public Character decode(String code) {
        HuffmanTreeNode current = (HuffmanTreeNode) queue.peek();
        if(current == null) return null;
        while(current.left != null || current.right != null) {
            if(current ==  null) throw new IllegalArgumentException("Invalid code");
            current = (code.charAt(0) == '0') ? current.left : current.right;
            code = code.substring(1);
        }
//        System.out.println(current.getKey());
        if(current ==  null) throw new IllegalArgumentException("Invalid code");
        return current.getKey();
    }

    public String decodeLong(String code) {
        HuffmanTreeNode current = (HuffmanTreeNode) queue.peek();
        String result = "";
        if(current == null) return null;
        while(code.length() > 0) {
//            System.out.println(code);
            while(current.left != null || current.right != null) {
                if(current ==  null) throw new IllegalArgumentException("Invalid code");
                current = (code.charAt(0) == '0') ? current.left : current.right;
                code = code.substring(1);
            }
            result += current.getKey();
            current = (HuffmanTreeNode) queue.peek();
        }
        if(current ==  null) throw new IllegalArgumentException("Invalid code");
        return result;

    }


    //May have to tweak the while condition if the entire thing isn't printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(queue.peek().toString());
        do {
            String addition = levelString((HuffmanTreeNode) queue.peek(), i);
            sb.append(addition);
            sb.append("\n");
            i++;
        }while (i < 2*Math.log(size)/Math.log(2)+1);
        return sb.toString();
    }

    private String levelString(HuffmanTreeNode node, int level) {
        String result = "";
        if(node == null) return "";
        if(level == 1) {

            result+= (node.left != null? node.left.toString() :"") + " , "  +
                    (node.right != null? node.right.toString() : "") + "      ";

        }
        else if (level > 1) {
            result += levelString(node.left, level-1);
            result += levelString(node.right, level-1);
        }
        return result;
    }
}
