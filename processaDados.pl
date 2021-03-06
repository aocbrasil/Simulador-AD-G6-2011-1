#!/usr/bin/perl -w
use strict;

my $arquivo = shift;
my $constIC = 1.96;

#MEDIAS

my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2);
my $soma = 0;
my $somaW1 = 0;
my $somaS1 = 0;
my $somaW2 = 0;
my $somaS2 = 0;
my $somaN1 = 0;
my $somaNq1 = 0;
my $somaN2 = 0;
my $somaNq2 = 0;

open(ARQUIVO, "<$arquivo") or die "Erro ao abrir arquivo";
while (<ARQUIVO>) {
	chomp;
	($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);
	$soma++;
	$somaW1 += $w1;
	$somaS1 += $s1;
	$somaW2 += $w2;
	$somaS2 += $s2;
	$somaN1 += $n1;
	$somaNq1 += $nq1;
	$somaN2 += $n2;
	$somaNq2 += $nq2;
#	print($id.";".($somaW1/$soma).";".($somaS1/$soma).";".($somaW2/$soma).";".($somaS2/$soma).";".($somaN1/$soma).";".($somaNq1/$soma).";".($somaN2/$soma).";".($somaNq2/$soma)."\n");
	
	
}
close(ARQUIVO);

my $mediaW1 = $somaW1/$soma;
my $mediaS1 = $somaS1/$soma;
my $mediaW2 = $somaW2/$soma;
my $mediaS2 = $somaS2/$soma;
my $mediaN1 = $somaN1/$soma;
my $mediaNq1 = $somaNq1/$soma;
my $mediaN2 = $somaN2/$soma;
my $mediaNq2 = $somaNq2/$soma;

#VARIANCIAS

my $somaVarW1 = 0;
my $somaVarS1 = 0;
my $somaVarW2 = 0;
my $somaVarS2 = 0;
my $somaVarN1 = 0;
my $somaVarNq1 = 0;
my $somaVarN2 = 0;
my $somaVarNq2 = 0;

open(ARQUIVO, "<$arquivo") or die "Erro ao abrir arquivo";
while (<ARQUIVO>) {
	chomp;
	my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);

	$somaVarW1 += ($w1-$mediaW1)**2;
	$somaVarS1 += ($s1-$mediaS1)**2;
	$somaVarW2 += ($w2-$mediaW2)**2;
	$somaVarS2 += ($s2-$mediaS2)**2;
	$somaVarN1 += ($n1-$mediaN1)**2;
	$somaVarNq1 += ($nq1-$mediaNq1)**2;
	$somaVarN2 += ($n2-$mediaN2)**2;
	$somaVarNq2 += ($nq2-$mediaNq2)**2;

}
close(ARQUIVO);

my $mediaVarW1 = $somaVarW1/$soma;
my $mediaVarS1 = $somaVarS1/$soma;
my $mediaVarW2 = $somaVarW2/$soma;
my $mediaVarS2 = $somaVarS2/$soma;
my $mediaVarN1 = $somaVarN1/$soma;
my $mediaVarNq1 = $somaVarNq1/$soma;
my $mediaVarN2 = $somaVarN2/$soma;
my $mediaVarNq2 = $somaVarNq2/$soma;

# VARIANCIAS DE W1 E W2

my $somaVarVarW1 = 0;
my $somaVarVarW2 = 0;

open(ARQUIVO, "<$arquivo") or die "Erro ao abrir arquivo";
while (<ARQUIVO>) {
	chomp;
	my ($id, $rodada, $w1, $s1, $w2, $s2, $n1, $nq1, $n2, $nq2) = split(";", $_);

	my $varW1 = ($w1-$mediaW1)**2;
	my $varW2 = ($w2-$mediaW2)**2;

	$somaVarVarW1 += ($varW1-$mediaVarW1)**2;
	$somaVarVarW2 += ($varW2-$mediaVarW2)**2;
}
close(ARQUIVO);

my $mediaVarVarW1 = $somaVarVarW1/$soma;
my $mediaVarVarW2 = $somaVarVarW2/$soma;

my $icW1 = $constIC * sqrt($mediaVarW1) / sqrt($soma);
my $icS1 = $constIC * sqrt($mediaVarS1) / sqrt($soma);
my $icW2 = $constIC * sqrt($mediaVarW2) / sqrt($soma);
my $icS2 = $constIC * sqrt($mediaVarS2) / sqrt($soma);
my $icN1 = $constIC * sqrt($mediaVarN1) / sqrt($soma);
my $icNq1 = $constIC * sqrt($mediaVarNq1) / sqrt($soma);
my $icN2 = $constIC * sqrt($mediaVarN2) / sqrt($soma);
my $icNq2 = $constIC * sqrt($mediaVarNq2) / sqrt($soma);
my $icVarW1 = $constIC * sqrt($mediaVarVarW1) / sqrt($soma);
my $icVarW2 = $constIC * sqrt($mediaVarVarW2) / sqrt($soma);

print "\n\tMEDIA \t\t\tIC\n";
print "W1:\t$mediaW1\t$icW1\n";
print "S1:\t$mediaS1\t$icS1\n";
print "W2:\t$mediaW2\t$icW2\n";
print "S2:\t$mediaS2\t$icS2\n";
print "N1:\t$mediaN1\t$icN1\n";
print "Nq1:\t$mediaNq1\t$icNq1\n";
print "N2:\t$mediaN2\t$icN2\n";
print "Nq2:\t$mediaNq2\t$icNq2\n";
print "VarW1:\t$mediaVarW1\t$icVarW1\n";
print "VarW2:\t$mediaVarW2\t$icVarW2\n\n";


