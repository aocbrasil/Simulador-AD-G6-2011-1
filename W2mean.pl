#!/usr/bin/perl -w
use strict;

my $file=shift;
my $totalRodadaW1=0;
my $totalLinhas=1;
my $numRodada = 0;
my $i=0;
my %W=();
my @sumW=(0);
my $sumW = 0;
my $mediaW=0;
my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2);
my $total = 1;

my $lines = `wc -l < $file`;
open(FILE, "<$file") or die "Erro ao abrir arquivo.";
while(<FILE>){
	chomp;
	($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);
	$W{$rodada} += $w2;
	$W{"total".$rodada} += $total;
}
close FILE;
my $size = scalar(keys %W);
while($i<$size/2){
	$mediaW = $W{$i}/$W{'total'.$i};
	print "$i;$mediaW\n";
	$i++;
}

# print "\tRODADA\tMEDIA W2\n";
# my $line_count = `wc -l < $file`;
# while($i<3){
# 	$total=0;
# 	$sumW=0;
# 	if($total>0){ $mediaW = $sumW / $total; }
# 	print "\t$rodada\t$mediaW\n";
# 	$i++;
# }	
	
#print "\nsomas\n";
#for ($i = 1; $i <= $#W1; $i++){
#	$sumW1[$i] = $sumW1[$i-1] + $W1[$i];
	#print "$sumW1[$i]\n";
#}
#print "\nmedias\n";
#$i=0;
#for ($i = 0; $i <= $#sumW1; $i++){
#	print "$i: ($mediaW1 + $sumW1[$i]) / $i = ";
#	$mediaW1 = ($mediaW1 + $sumW1[$i]) / ($i==0?1:$i);
#	print "$mediaW1\n";
#}