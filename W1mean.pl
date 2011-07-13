#!/usr/bin/perl -w
use strict;

my $file=shift;
my $totalRodadaW1=0;
my $totalLinhas=1;
my $numRodada = 0;
my $i=0;
my @W1=();
my @W2=();
my @sumW1=(0);
my @sumW2=(0);
my $mediaW1=0;
my $mediaW2=0;
my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2);

#print "@W1 ; @sumW1\n";
open(FILE, "<$file") or die "Erro ao abrir arquivo.";
while(<FILE>){
	chomp;
	($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);
	push(@W1,$w1);
	push(@W2,$w1);
#	print "@W1\n"
}
#print "\nsomas\n";
for ($i = 1; $i <= $#W1; $i++){
	$sumW1[$i] = $sumW1[$i-1] + $W1[$i];
	$sumW2[$i] = $sumW2[$i-1] + $W2[$i];
	#print "$sumW1[$i]\n";
}
#print "\nmedias\n";
$i=0;
for ($i = 0; $i <= $#sumW1; $i++){
#	print "$i: ($mediaW1 + $sumW1[$i]) / $i = ";
	$mediaW2 = ($mediaW2 + $sumW2[$i]) / ($i==0?1:$i);
	$mediaW1 = ($mediaW1 + $sumW1[$i]) / ($i==0?1:$i);
	print "$mediaW1;$mediaW2\n";
}

