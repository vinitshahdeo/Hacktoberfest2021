#include <iostream>

using namespace std;

int main() {

	int t;	long long int d,D,n,p,q,c;

	cin>>t;

	while(t-- >0)

	{

	    cin>>D>>d>>p>>q;

	    n=D/d;

	    c=((n*d)*(2*p+(n-1)*q))/2 + (D-n*d)*(p+n*q);

	    cout<<c<<"\n";

	}

	return 0;
