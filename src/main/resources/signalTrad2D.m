trajs = SG.P.traj;
time = trajs{1,1}.time;
time2 = time(3:length(time));
s1 = trajs{1,1}.X(1,:);
s2 = s1(3:length(s1));
s1 = s1(1:1999);


%%% time
fileID = fopen('time.txt','w');
fprintf(fileID,'%f,',time2(1:end-1));
fprintf(fileID,'%f',time2(end));
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
    s2 = s(3:length(s));
    s1 = s(1:length(s2));
    fprintf(fileID,'%f,',s1(1:end-1));
    fprintf(fileID,'%f',s1(end));
    fprintf(fileID,'\n');
    fprintf(fileID,'%f,',s2(1:end-1));
    fprintf(fileID,'%f',s2(end));
    fprintf(fileID,'\n');
end

fileID = fopen('trajxy.txt','w');
for i=1:length(trajs)
    s = trajs{1,i}.X(1,:);
    s2 = s(3:length(s));
    s1 = s(1:length(s2));
    fprintf(fileID,'%f,',s1(1:end-1));
    fprintf(fileID,'%f',s1(end));
    fprintf(fileID,'\n');
end
for i=1:length(trajs)
    s = trajs{1,i}.X(1,:);
    s2 = s(3:length(s));
    s1 = s(1:length(s2));
    fprintf(fileID,'%f,',s2(1:end-1));
    fprintf(fileID,'%f',s2(end));
    fprintf(fileID,'\n');
end


fclose(fileID);