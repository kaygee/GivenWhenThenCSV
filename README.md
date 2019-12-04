# GivenWhenThenCSV
This project is to input a CSV (output from Microsoft PICT) and output something that looks like Gherkin.

# Convert TSV to CSV
The output of Microsoft PICT is a TSV. Let's convert that to a CSV.

Example

```gsed 's/\t/,/g' Rough\ Draft\ Pairwise\ Output.tsv >Rough\ Draft\ Pairwise\ Output.csv```