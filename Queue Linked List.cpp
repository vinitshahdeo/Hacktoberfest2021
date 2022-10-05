#include <stdio.h>
#include <stdlib.h>

struct Node{
    int data;
    struct Node *next;
    
};

struct Node *f;
struct Node *r;

void enqueue(int val){
    struct Node *n=(struct Node *)malloc(sizeof(struct Node));
    if (n==NULL){
        printf("Queue is full");
        return;
    }
    else{
        n->data=val;
        if (f==NULL){
            f=r=n;
            f->next=NULL;
            r->next=NULL;
            
        }else{
            r->next=n;
            r=n;
            r->next=NULL;
        }
    }
}

void dequeue(){
    struct Node* ptr=f;
    if (f==NULL){
        printf("-1");
        return;
    }	 	  	 	 		   	    	  	    	     	 	
    else{
        f=f->next;
        free(ptr);
    }
}

void display(struct Node *ptr){
    while (ptr!=NULL){
        printf("%d ",ptr->data);
        ptr=ptr->next;
    }
    printf("\n");
}

void peekfront(struct Node *ptr){
    printf("%d\n",ptr->data);
}

void sizequeue(struct Node *ptr){
    int count=0;
    while (ptr!=NULL){
        count=count+1;
        ptr=ptr->next;
    }
    printf("%d\n",count);
}

int main(){
    
    //enqueue-1  dequeue-2  display-3  peekfront-4  size-5
    
    for (int i=0;i<15;i++){
        int choice=1;
        int x;
        scanf("%d\n",&choice);
        
        switch(choice){	 	  	 	 		   	    	  	    	     	 	
            case 1:
            scanf("%d\n",&x);
            enqueue(x);
            break;
            case 2:
            dequeue();
            break;
            case 3:
            display(f);
            break;
            case 4:
            peekfront(f);
            break;
            case 5:
            sizequeue(f);
            break;
        }
    }
    
    
    return 0;
}