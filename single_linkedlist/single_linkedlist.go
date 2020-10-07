package main

import (
	"errors"
	"fmt"
)

type Element struct {
	Value interface{}
}

type Node struct {
	next *Node
	data Element
}

type LinkedList struct {
	head *Node
	Size int
}

func (ll *LinkedList) Get(index int) (Element, error) {
	if index < 0 || index > ll.Size-1 {
		return Element{}, errors.New("Index out of bound")
	}
	n := ll.head
	for i := 0; i < ll.Size; i++ {
		if i == index {
			return Element{n.data}, nil
		}
		n = n.next
	}
	return Element{}, nil
}

func (ll *LinkedList) AddAt(index int, d interface{}) error {
	if index < 0 || index > ll.Size-1 {
		return errors.New("Index out of bound")
	}
	if index == 0 {
		ll.AddFirst(d)
		return nil
	}
	node := &Node{data: Element{d}}
	n := ll.head
	for i := 0; i < index-1; i++ {
		n = n.next
	}
	node.next = n.next
	n.next = node
	ll.Size = ll.Size + 1
	return nil
}

func (ll *LinkedList) Add(d interface{}) {
	node := &Node{data: Element{d}}
	if ll.head == nil {
		ll.head = node
	} else {
		n := ll.head
		for n.next != nil {
			n = n.next
		}
		n.next = node
	}
	ll.Size = ll.Size + 1
}

func (ll *LinkedList) AddLast(d interface{}) {
	ll.Add(d)
}

func (ll *LinkedList) AddFirst(d interface{}) {
	node := &Node{data: Element{d}}
	node.next = ll.head
	ll.head = node
	ll.Size = ll.Size + 1
}

func (ll *LinkedList) Replace(index int, d interface{}) error {
	if index < 0 || index > ll.Size-1 {
		return errors.New("Index out of bound")
	}
	n := ll.head
	for i := 0; i < ll.Size; i++ {
		if i == index {
			n.data.Value = d
			break
		}
		n = n.next
	}
	return nil
}

func (ll *LinkedList) String() string {
	res := "["
	n := ll.head
	for n != nil {
		res = res + fmt.Sprintf("%v", n.data.Value)
		n = n.next
		if n != nil {
			res = res + " "
		}
	}
	res = res + "]"
	return res
}
