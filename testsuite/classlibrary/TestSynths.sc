/*
TestSynths.run
UnitTest.gui
*/
TestSynths : UnitTest {
	setUp {
		Server.default.quit;
		Server.default.options.memSize = 65536;
		this.bootServer;
	}

	/* to add more tests use the following to pin the sample value
	{}.loadToFloatArray(0.123, action: {|array| array.last.postln});
	*/

	test_tweets {
		switch(Server.default.sampleRate.asInteger,
			44100, {
				this.pinTests([
					{var b,a=LFTri;Splay.ar(Blip.ar([60,65,53,67,80].midicps,(b=(1..5))**ModDif.ar(a.ar(1),a.ar(2),a.ar(4))*a.ar(1/b/9)*9)).tanh} -> 0.18580715358257,
					{var b,a=LFSaw;Splay ar:RLPF.ar(a.ar(a.ar(b=1/2**(2..6))>0*3+15/b),pi**a.ar(a.ar(b)+1*b)*999+(a.ar(a.ar(b*9)*9)*99)/2,0.2)/5} -> 0.039695084095001,
					{var c,b,a=LFTri;Splay.ar(FBSineC.ar(2**a.ar(pi/b=7.fib).ceil*99*(2**b)%((c=a.ar(b/99)+1)*4e3),c+0.1,a.ar(1/b/20)+1,1.02,7)/3)} -> -0.30997920036316,
					{var b,a=LFCub;Splay.ar(a.ar(b=2/(2..9))%a.ar(b/5)*a.ar(2**a.ar(b/8)>0+1*2*(b*[300,303]-(a.ar(b/9)>0*50).lag2))*a.ar(b/6,b))} -> -0.0054647326469421,
					{var b,a=SinOscFB;Splay.ar(AllpassN.ar(a.ar(999*b=1/(a.ar(1/(4..9))>0*[1,4,2,3]+4),a.ar(a.ar(b)>0,b,b)%1).tanh/2,1,b.lag,4))} -> -0.051101461052895,
					{var b,a=SinOscFB;Splay.ar(a.ar(a.ar(b=1/(2..6),1)<b*500+99)/5+a.ar(999*b,a.ar(a.ar(b,1)<0.1+1,1)%b,a.ar(0.1-b,1).min(0)))/2} -> 0.15436314046383,
					{var c,a=LFTri;RHPF.ar(Splay ar:SinOsc.ar(a.ar(c=[6,14,4])%a.ar(c),a.ar(c-1)+1**a.ar(c/9)*4,a.ar(1/c)>0*99),99,0.3).tanh*0.6} -> 0.16477671265602,
					{var c,b,a=LFSaw;a.ar((b=a.ar(1/3))+1**a.ar(b)*(99+c=[0,1]))%a.ar(b*99,c)%a.ar(1/32)+a.ar(a.ar(b)*4e4%2e3,0,a.ar(6,c)>0.9/2)/2} -> 0.0038245529867709,
					{var a=Blip;GVerb.ar(HPF.ar(a.ar(a.ar(1/4,8).ceil+1*[99,9])*a.ar(1.01,ceil(2**a.ar(1/16)*4))>(a.ar(1/128)/4),9)/9,9,2,0.9)} -> -0.007532590534538,
					{var a=LFSaw;HPF.ar(Splay.ar(Saw.ar(midicps(':>AEH.'.ascii-ceil(2**a.ar((1..5)/32))))%a.ar(1!2++6)%a.ar(2,[1,2]/8,2)),9)/2} -> -0.02206714823842,
					{var c,b,a=SinOscFB;Splay.ar(a.ar(SelectX.ar(a.ar(0.1)%(a.ar(b=(1..4)))*(c=b+8),DC ar:':.UODD.Ed'.ascii.midicps),a.ar(1/c)))/2} -> 0.28094059228897,
					{var t,b;GVerb.ar(sum(SinOscFB.ar(33*b=(1..50),lagud(t=Impulse.ar(b/49),5e-3,0.2)*99,t.lagud(7e-3,1))),299,9,0.01,0.5,6,1,1,1)} -> 0.00020657968707383,
					{var d,b,a=Saw;RecordBuf.ar(a.ar(d=[2,4,8,3,6])%a.ar(9)/6,b=LocalBuf(3e3,5).clear);Splay.ar(Shaper.ar(b,a.ar(d*32+a.ar(d/8))))} -> -0.033640503883362,
					{var c,a,b=XFade2;a=SinOscFB;a.ar(b.ar(a.ar(1).ceil*36,a.ar(2).round*2+4*12,a.ar(1/[4,12]))+8*4,c=a.ar([2,3])%1,c*a.ar(1,1/3))} -> -0.60460704565048,
					{var c,b,a=LFSaw;b=Formant;b.ar(round(a.ar(1/16),c=3**a.ar([2,3],[0,1]))+3*33*ceil(c),3**c.lag*66,3**c*99)+b.ar(c+3,1-c*3e3)/4} -> 0.45729434490204,
					{var d,a=SinOscFB;LocalOut kr:d=a.ar(Duty.kr(LocalIn kr:8,a.kr(1/16,1),Dseq((1..8)*50,inf)),a.kr(1/(1..8))+1/2);Splay ar:d/2} -> 0.047290273010731,
					{var b,a=SinOsc;GVerb.ar(sum(RHPF.ar(a.ar(3.5-b=(8..2),a.ar(4/b)*99)>0*a.ar(b*99)*(9-b),a.ar(1/b/2)+2*666,0.4)).tanh/5,99,2)} -> -0.11517204344273,
					{var b,a=SinOsc;Splay ar:a.ar(Duty.ar(1/b=(2..6),0,Dseq(a.ar(0.1)>0*b+ceil(b*a.ar(3/b))%14*99,inf)),b*b*tanh(a.ar(4/b)*9)|1)} -> 0.22148554027081,
					{HPF.ar(FreeVerb.ar({|i|SinOsc.ar(Duty.ar(i+1/9,0,Dseq((1..8).stutter(32),inf)*Dseq(8.fib,inf)*99))}!2,0.2,1,0.2),9)/2} -> 0.38820672035217,
					{var b,a;FreqShift.ar(a=Splay.ar(Formlet.ar(Blip.ar(Blip.ar(Blip.ar(2.01,3)>0,b=(1..9))+1,b/8)+2*99,b*50,0.01).tanh),0.01)+a/7} -> 0.10917799174786,
					{var c,b,a=SinOscFB;GVerb.ar(mean(FreqShift.ar(c=a.ar(a.ar(a.ar(99/b=(1..9),1),1/b)+b*50,1),1/b)+c)/3,200,3,0.5,0.5,9,1,0.7,1)} -> -0.13149619102478,
					{var c,b,a=SinOscFB;LocalOut ar:c=a.ar(Duty.ar(Trig.ar(LocalIn ar:2,a.ar(b=1/[3,2])+11/2),0,Dseq((1..8),inf))*99,a.ar(b/12));c} -> -0.5732364654541,
					{var b,a=Blip;b=(1..8);Splay.ar(a.ar(a.ar(3.125,b)+a.ar(b/2.45,b)+1*a.ar(8/b,50)+1*99,a.ar(b/pi,b)+b-a.ar(1/4/b,2,4)).sin/2)} -> -0.055294871330261,
					{var c,a=LFSaw;Formlet.ar(Formlet.ar(a.ar(a.ar(c=a.ar([1,2]/32)<0+1)>0+c.lag(c)*99),0,a.ar(3-c/[4,3])+1).sin,99,0,0.01).tanh} -> 0.18585808575153,
					{var c,b,a=VarSaw;Splay ar:a.ar(Select.ar(a.ar(2.01/b=[0,3,7,5,2,9,10]+0.2)*8,(c=a.ar(0.5/b))>0*12+b+48).midicps,0,c%1).tanh/2} -> 0.34462589025497,
					{var b,a=SinOscFB;Splay.ar(a.ar(13*13*b=(1..3),1/3)*a.ar(b/13,1)/13+a.ar(a.ar(1/(13..3))+133*b,a.ar(b/333,a.ar(b,1)%1)%1))/3} -> -0.0049750884063542,
					{var c,b,a=SinOscFB;Splay ar:a.ar(collect(b=(1..8),{|x,i|[x,i+6/6e3+x]})*60,c=a.ar(b/16/16.16)%1,a.ar([3,6],1,c.lag/3).max(0))} -> -0.00088413717458025,
					{var b,a=VarSaw;Splay ar:CombC.ar(a.ar(a.ar(1/b=[2,4,9,3]*9)>0+3*b,0,lag(a.ar(b/2e3)+1/2,1)),1.1,round(a.ar(8/b)%1)+0.1,8)/3} -> 0.060873653739691,
					{var c,b,a=SinOsc;c=a.ar(b=(1..6)*60,LocalIn.ar(6)*3);LocalOut.ar(Limiter.ar(BPF.ar(c,a.ar(16/b)+3*b),0.66,16/b));Splay ar:c/2} -> 0.013813856989145,
					{var c,a=SinOscFB;Splay ar:a.ar(midicps(c=a.ar(12.fib/round(a.ar(1/[2,4])%1+0.125),1)>0*[9,2,3,0,7,5]+55),a.ar(c/999)+1/2)/3} -> 0.65228378772736,
					{var b,a=SinOsc;Splay ar:a.ar(b=(1..7)/7,a.ar(b/77)+1*7**a.ar(777*b,77**a.ar(b+a.ar(b/77,a.ar(b)/7,77*a.ar(b/77,b,b*7)))))/2} -> 0.67458367347717,
					{var c;Splay ar:HPF.ar(BLowPass.ar(LFTri ar:c=[1,3,5,6],Duty.ar(c+1/16,0,Dseq(LFTri.ar(1/c/8)>0*3+c*99,inf)),1e-3)/12,9).sin} -> -0.0025723010767251,
					{var c,a=SinOscFB;Out.kr(8,c=a.ar(1/4)>0*a.ar(3/4,1,99,199).round(20)+DelayN.ar(In kr:[9,8],2,2)%[72,64]);a.ar(c**2,c.lag%1)} -> 0.58804649114609,
					{var a=SinOsc;a.ar(0,a.ar(99,9**a.ar(1/8,lagud(a.ar(1/[2,12])<0*2**a.ar(a.ar(3)>0*150),1/50,1/5)*5))+a.ar(1/[6,7]),3).tanh} -> 0.95214116573334
				]);
			},
			{
				"TestSynths : no pinning tests found for current samplerate - skipping".warn;
			}
		);
	}
	test_other {
		switch(Server.default.sampleRate.asInteger,
			44100, {
				this.pinTests([
					{SinOscFB.ar(100,0.9)*Saw.ar(200,0.8)*VarSaw.ar(300,1,0.2,0.7)} -> 0.026012329384685,
					{Blip.ar(600,700,0.9)%LFTri.ar(0.4,1.5,0.8)+LFPar.ar(5,-3,0.7)} -> 0.73242437839508,
					{EnvGen.ar(Env.perc, Impulse.ar(1))*CombL.ar(SyncSaw.ar(500,600,0.9),0.01,0.01,2)} -> -3.4441709518433,
					{LeakDC.ar(LFCub.ar(3,0.5,0.9)+LFPulse.ar(400,0.2,0.2),0.996)} -> -0.29306599497795
				]);
			},
			48000, {
				this.pinTests([
					{SinOscFB.ar(100,0.9)*Saw.ar(200,0.8)*VarSaw.ar(300,1,0.2,0.7)} -> 0.028196411207318,
					{Blip.ar(600,700,0.9)%LFTri.ar(0.4,1.5,0.8)+LFPar.ar(5,-3,0.7)} -> 0.49589711427689,
					{EnvGen.ar(Env.perc, Impulse.ar(1))*CombL.ar(SyncSaw.ar(500,600,0.9),0.01,0.01,2)} -> -5.6630301475525,
					{LeakDC.ar(LFCub.ar(3,0.5,0.9)+LFPulse.ar(400,0.2,0.2),0.996)} -> -0.28865760564804
				]);
			},
			{
				"TestSynths : no pinning tests found for current samplerate - skipping".warn;
			}
		);
	}

	pinTests {|tests|
		var precision = 0.0001;  //with 0.0000000000001 it passes all tests on osx but all fail on rpi, the rpi need 0.001
		var testsIncomplete;
		testsIncomplete = tests.size;
		tests.do{|assoc|
			assoc.key.loadToFloatArray(0.123, action: {|array|
				this.assertFloatEquals(array.last, assoc.value, assoc.key.def.sourceCode, precision);
				testsIncomplete = testsIncomplete-1;
			});
		};
		this.wait{testsIncomplete==0};
	}
}

/*
{LeakDC.ar(LFCub.ar(3,0.5,0.9)+LFPulse.ar(400,0.2,0.2),0.996)}
.loadToFloatArray(0.123, action:{|a| a.last.postln})
*/
