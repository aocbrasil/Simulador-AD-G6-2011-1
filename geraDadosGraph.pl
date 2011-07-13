#!/usr/bin/perl -w
use strict;
use Switch;


if($#ARGV != 1){
	print "Uso incorreto do programa.\n";
	print $#ARGV."\n";
	exit 0;
}

my $file=shift;
my $item=shift;
my $totalRodadaW1=0;
my $totalLinhas=1;
my $numRodada = 0;
my $i=0;
my (@W1,@W2,@S1,@S2,@N1,@N2,@Nq1,@Nq2)=();
my @sumW1=(0);
my $mediaW1=0;
my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2);
my @temp = ();

print $item;

open(FILE, "<$file") or die "Erro ao abrir arquivo.";
while(<FILE>){
	chomp;
	($id,$rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);
	push(@W1,$w1);
	push(@S1,$s1);
	push(@W2,$w2);
	push(@S2,$s2);
	push(@N1,$n1);
	push(@Nq1,$nq1);
	push(@N2,$n2);
	push(@Nq2,$nq2);
}

switch($item){
	case "w1" { @temp = @W1; }
	case "w2" { @temp = @W2; }
	case "s1" { @temp = @S1; }
	case "s2" { @temp = @S2; }
	case "n1" { @temp = @N1; }
	case "n2" { @temp = @N2; }
	case "nq1"{ @temp = @Nq1;}
	case "nq2"{ @temp = @Nq2;}
}
#print "\nsomas\n";
for ($i = 1; $i <= $#temp; $i++){
	$sumW1[$i] = $sumW1[$i-1] + $temp[$i];
	#print "$sumW1[$i]\n";
}
#print "\nmedias\n";
$i=0;
for ($i = 0; $i <= $#sumW1; $i++){
#	print "$i: ($mediaW1 + $sumW1[$i]) / $i = ";
	$mediaW1 = ($mediaW1 + $sumW1[$i]) / ($i==0?1:$i);
	print "$mediaW1\n";
}