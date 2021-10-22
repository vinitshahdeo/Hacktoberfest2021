/* 149. Maximum Points in a Line
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, 
return the maximum number of points that lie on the same straight line.

Examples:
1.  Input: points = [[1,1],[2,2],[3,3]]
    Output: 3
2.  Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
*/

// SOLUTION 1:
#include <bits/stdc++.h>

int maxPoints(vector<vector<int>>& points) {
        
        int n = points.size();
        if(n <= 1)  return n;
        unordered_map<double, int> lines;
        int max_p = 1;
        for(int i=0; i < n; i++) {
            lines.clear();
            for(int j = i+1; j < n; j++) {
                double slope = 0;
                if(points[i][0] == points[j][0])    
                    slope = INT_MAX;
                else
                    slope = ((double)(points[i][1] - points[j][1]) / (double)(points[i][0] - points[j][0]));
                if(lines.count(slope))
                    lines[slope]++;
                else
                    lines[slope] = 1;
            }
            for(auto l: lines)
                max_p = max(max_p, l.second + 1);
        }
        return max_p;
        // int n = points.size();
        // if(n <= 2)  return n;
        // int maxi = 2;
        // for(int i = 0;i < n; i++) {
        //     for(int j = i + 1; j < n; j++) {
        //         int tot = 2;
        //         for(int k=0; k < n; k++) {
        //             if(k != i && k != j) {
        //                 if( (points[i][1] - points[j][1])*(points[k][0] - points[i][0]) == (points[k][1] - points[i][1])*(points[i][0] - points[j][0]) )
        //                     tot++;
        //             }
        //         }
        //         maxi = max(maxi,tot);
        //     }
        // }
        // return maxi;
    }

// SOLUTION 2:
int maxPoints(vector<vector<int>>& points) {
        int n = points.size();
        if(n <= 2)  return n;
        int maxi = 2;
        for(int i = 0;i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int tot = 2;
                for(int k=0; k < n; k++) {
                    if(k != i && k != j) {
                        if( (points[i][1] - points[j][1])*(points[k][0] - points[i][0]) == (points[k][1] - points[i][1])*(points[i][0] - points[j][0]) )
                            tot++;
                    }
                }
                maxi = max(maxi,tot);
            }
        }
        return maxi;
    }