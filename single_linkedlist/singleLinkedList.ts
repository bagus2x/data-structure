class SingleNode<T> {
    next: SingleNode<T>;
    data: T;
}

export default class SingleLinkedList<T> {
    private head: SingleNode<T>;
    private _size: number = 0;

    get size(): number {
        return this._size;
    }

    isEmpty(): boolean {
        return this._size === 0;
    }

    get(index: number): T {
        let res: T;
        let n = this.head;
        for (let i = 0; i < this._size; i++) {
            if (i === index) {
                res = n.data;
                break;
            }
            n = n.next;
        }
        return res;
    }

    add(data: T): void {
        let singleNode = new SingleNode<T>();
        singleNode.data = data;
        if (!this.head) {
            this.head = singleNode;
        } else {
            let n = this.head;
            while (n.next) {
                n = n.next;
            }
            n.next = singleNode;
        }
        this._size++;
    }

    // [1,a], [2,b], [ x, y ], [3,c]
    addAt(index: number, data: T) {
        if (index > this._size - 1 || index < 0)
            throw new Error("Index Out Of bound");
        if (index === 0) return this.addFirst(data);
        let singleNode = new SingleNode<T>();
        let n = this.head;
        singleNode.data = data;
        for (let i = 0; i < index - 1; i++) {
            n = n.next;
        }
        singleNode.next = n.next;
        n.next = singleNode;
        this._size++;
    }

    addFirst(data: T) {
        let singleNode = new SingleNode<T>();
        singleNode.data = data;
        singleNode.next = this.head;
        this.head = singleNode;
        this._size++;
    }

    replace(index: number, data: T) {
        if (index > this._size - 1) throw new Error("Index out of bound");
        let n = this.head;
        for (let i = 0; i < this._size; i++) {
            if (i === index) {
                n.data = data;
                break;
            }
            n = n.next;
        }
    }

    replaceOne(before: T, after: T) {
        let n = this.head;
        while (n.next) {
            if (n.data === before) {
                n.data = after;
                break;
            }
            n = n.next;
        }
    }

    replaceAll(before: T, after: T) {
        let n = this.head;
        while (n.next) {
            if (n.data === before) {
                n.data = after;
            }
            n = n.next;
        }
    }

    removeFirst() {
        if (!this.head) throw new Error("Empty Linked List");
        this.head = this.head.next;
        this._size--;
    }

    removeLast() {
        if (this.size === 1) return this.removeFirst();
        let n = this.head;
        while (n.next.next) {
            n = n.next;
        }
        n.next = undefined;
        this._size--;
    }

    // [1,a], [2,b], [ x, y ], [3,c]
    removeAt(index: number) {
        let n = this.head;
        if (index === 0) return this.removeFirst();
        for (let i = 0; i < index - 1; i++) {
            n = n.next;
        }
        n.next = n.next.next;
        this._size--;
    }

    toString() {
        let res = "[";
        let n = this.head;
        while (n) {
            res += n.data;
            n = n.next;
            if (n) {
                res += ", ";
            }
        }
        return res + "]";
    }
}
