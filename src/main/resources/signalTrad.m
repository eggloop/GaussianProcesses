trajs = SG.P.traj;
time = trajs{1,1}.time;
s1 = trajs{1,1}.X(2,:);

%%% time
fileID = fopen('time.txt','w');
fprintf(fileID,'%f,',time(1:end-1));
fprintf(fileID,'%f',time(end));
fclose(fileID);

label=[];
for i=1:length(val)
    if val(i)>0
        label=[label,1];
    else
        label=[label,-1];
    end
end
fileID = fopen('label.txt','w');
fprintf(fileID,'%i,',label(1:end-1));
fprintf(fileID,'%i',label(end));
fclose(fileID);

fileID = fopen('traj.txt','w');
for i=1:length(trajs)
    s = trajs{1,i}.X(1,:);
    fprintf(fileID,'%f,',s(1:end-1));
    fprintf(fileID,'%f',s(end));
    fprintf(fileID,'\n');
end
fclose(fileID);