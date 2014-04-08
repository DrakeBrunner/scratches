#include <stdio.h>
#include <string.h>
#include "llist-plus.h"

/* Insert Nth Node to list */
void InsertNodeNth(Node **top, int n, int no, char *name) {
    // ここに解答を書き加える
    Node *ptr = *top;

    // n = 1では必ずInsertNode
    if (n == 1) {
        InsertNode(top, no, name);
        return;
    }

    int i;
    // ptr(カーソル)をnまで移動
    for (i = 0; i < n - 1; i++) {
        ptr = ptr->next;

        // 3つしかないリストにn=8など無茶されたとき
        if (ptr == NULL)
            return;

    }

    // バックアップ的なもの
    Node backup = *ptr;

    // ここでn個目に追加する
    SetNode(ptr, no, name, ptr->next);

    // 残った要素を1つずつずらす
    // backupとリストの中の要素を交換するためのtemp
    Node temp;
    while (ptr->next != NULL) {
        ptr = ptr->next;
//printf("address of ptr: %p\n", ptr);
//printf("*ptr->no: %d\n", ptr->no);

        SetNode(&temp, ptr->no, ptr->name, NULL);
        SetNode(ptr, backup.no, backup.name, ptr->next);
        SetNode(&backup, temp.no, temp.name, NULL);

    }

    // 要素がひとつ増えるのでAppend
    AppendNode(top, backup.no, backup.name);
}

/* Delete Nth Node from list */
void DeleteNodeNth(Node **top, int n) {
    // ここに解答を書き加える
    // nが1ならはじめの要素を消す
    if (n == 1) {
        DeleteNode(top);
        return;
    }
    Node *ptr = *top;
    int count = 1;
    while (ptr->next != NULL) {
        ptr = ptr->next;
        count++;
    }
    // nが要素数より大きい時
    if (n > count)
        return;

    if (ptr == NULL)
        return;

    ptr = *top;

    Node backup = *ptr;
    Node temp;
    int i;
    for (i = 0; i < n - 1; i++) {
        ptr = ptr->next;

        SetNode(&temp, ptr->no, ptr->name, NULL);
        SetNode(ptr, backup.no, backup.name, ptr->next);
        SetNode(&backup, temp.no, temp.name, NULL);
    }

    DeleteNode(top);
}
