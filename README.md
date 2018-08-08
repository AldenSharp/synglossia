SYNGLOSSIA API
=

This API retrieves data from the Synglossia database, automatically constructing new data forms and populating defaults.

Endpoints
-

All endpoints take the URI `https://c1hj6zyvol.execute-api.us-east-1.amazonaws.com/prod/`.

#### Get list of parent languages.

```
GET /languages
```

Every parent language name is also the name of its corresponding syngloss. 

#### Get syngloss.

```
GET /syngloss/{name}
```

#### Get a random word in a given syngloss.

```
GET /syngloss/{name}/word
```

#### Get a random noun in a given syngloss.

```
GET /syngloss/{name}/noun
```

Conditions
-

This is data class that records the conditions in which phenomena occur in transcribing spoken information into writing systems.

This data class is applied to word objects one phoneme position at a time in each syllable.

All out-of-bounds syllable and phoneme position references simply return false.

Conditions have the following types.

#### DEFAULT

The categorical condition. This condition does not have to be explicated in the database, because it is assumed when not specified.

This condition has no other fields.

#### NOT

Negation of condition.

Required fields:

```
condition: <condition>
```

#### AND

Conjunction of conditions.

Required fields:

```
conditions: [<condition>]
```

#### OR

Disjunction of conditions.

Required fields:

```
conditions: [<condition>]
```

#### BEFORE

The current position is immediately before a certain phoneme in the `values` list.

Required fields:

```
adjacentSound: { type: 'CONSONANT' / 'VOWEL' / 'ANY', values: [<phoneme string>] }
```

#### AFTER

The current position is immediately after a certain phoneme in the `values` list.

Required fields:

```
adjacentSound: { type: 'CONSONANT' / 'VOWEL' / 'ANY', values: [<phoneme string>] }
```

#### CONSONANTAL

Phoneme is not located at the syllable core.

This condition has no other fields.

#### SYLLABIC

Phoneme is located at the syllable core.

This condition has no other fields.

#### WORD_INITIAL

Phoneme is the first non-zero value in the word.

This condition has no other fields.

#### WORD_FINAL

Phoneme is the last non-zero value in the word.

This condition has no other fields.

#### SYLLABLE_INITIAL

Phoneme is the first non-zero value in the syllable.

This condition has no other fields.

#### SYLLABLE_FINAL

Phoneme is the last non-zero value in the syllable.

This condition has no other fields.

#### SOUND_VALUES

Phoneme sound value is some value in the `values` array.

Required fields:

```
values: [<phoneme string>]
```

#### EMPTY

Phoneme sound value is empty.

This condition has no other fields.

#### SYLLABLE_COUNT

Number of syllables in the word are greater than, less than, or equal to the `count` value.

Required fields:

```
comparison: 'GREATER_THAN' / 'LESS_THAN' / 'EQUALS'
count: <int>
```

Syllable Conditions
-

This is data class that records the conditions in which a given word is valid in the parent language, and in which phenomena occur in the evolutions of words.

This data class is applied to word objects one whole syllable position at a time, and thus assume an inherent syllable index value.

Syllable conditions have the following types.

#### DEFAULT

The categorical syllable condition. This syllable condition does not have to be explicated in the database, because it is assumed when not specified.

This syllable condition has no other fields.

#### NOT

Negation of syllable condition.

Required fields:

```
condition: <syllable condition>
```

#### AND

Conjunction of syllable conditions.

Required fields:

```
conditions: [<syllable condition>]
```

#### OR

Disjunction of syllable conditions.

Required fields:

```
conditions: [<syllable condition>]
```

#### WORD_MEDIAL_CLUSTERS

The word-medial cluster immediately after the current syllable is some value in the `values` list, including empty positions.

At the final syllable, this returns true vacuously.

It is not recommended to use `SYLLABLE_INITIAL_CLUSTERS` and `SYLLABLE_FINAL_CLUSTERS` in conjunction with `WORD_MEDIAL_CLUSTERS`, as these are redundant.

Required fields:

```
values: [[<phoneme string>]]
```

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### WORD_INITIAL_CLUSTERS

The word-initial cluster is some value in the `values` list, including empty positions.

Required fields:

```
values: [[<phoneme string>]]
```

#### WORD_FINAL_CLUSTERS

The word-final cluster is some value in the `values` list, including empty positions.

Required fields:

```
values: [[<phoneme string>]]
```

#### SYLLABLE_INITIAL_CLUSTERS

The syllable-initial cluster is some value in the `values` list, including empty positions.

It is not recommended to use `SYLLABLE_INITIAL_CLUSTERS` and `SYLLABLE_FINAL_CLUSTERS` in conjunction with `WORD_MEDIAL_CLUSTERS`, as these are redundant.

Required fields:

```
values: [[<phoneme string>]]
```

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### SYLLABLE_FINAL_CLUSTERS

The syllable-final cluster is some value in the `values` list, including empty positions.

It is not recommended to use `SYLLABLE_INITIAL_CLUSTERS` and `SYLLABLE_FINAL_CLUSTERS` in conjunction with `WORD_MEDIAL_CLUSTERS`, as these are redundant.

Required fields:

```
values: [[<phoneme string>]]
```

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### BEFORE

The current position is immediately before a certain phoneme in the `adjacentSound.values` list.

Required fields:

```
position: { syllable: <int>, sound: <int> }
adjacentSound: { type: 'CONSONANT' / 'VOWEL' / 'ANY', values: [<phoneme string>] }
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### AFTER

The current position is immediately after a certain phoneme in the `adjacentSound.values` list.

Required fields:

```
position: { syllable: <int>, sound: <int> }
adjacentSound: { type: 'CONSONANT' / 'VOWEL' / 'ANY', values: [<phoneme string>] }
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### SOUND_VALUES

Phoneme sound value at the position in the syllable is some value in the `values` list.

Required fields:

```
position: { syllable: <int>, sound: <int> }
values: [<phoneme string>]
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### EMPTY

Phoneme sound value at this position in the syllable is empty.

Required fields:

```
position: { syllable: <int>, sound: <int> }
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### EMPTY_AT_ALL

Phoneme sound value at every position in the `positions` list is empty.

If the `positions` list is singleton, then it is recommended to use `EMPTY` instead.

Required fields:

```
positions: [{ syllable: <int>, sound: <int> }]
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### EMPTY_AT_SOME

Phoneme sound value at some position in the `positions` list is empty.

If the `positions` list is singleton, then it is recommended to use `EMPTY` instead.

Required fields:

```
positions: [{ syllable: <int>, sound: <int> }]
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### OPEN

Every coda consonant position in the syllable has zero sound value.

There are no required fields.

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### BEFORE_HIATUS

Every consonant position between this syllable's core and the next syllable's core (exclusive) has zero sound value.

If the syllable is final, then this condition returns false.

There are no required fields.

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### SYLLABLE_POSITION

This syllable is at the specified position value. It is always absolute.

Position value is Python-style - it can be negative.

Required fields:

```
position: <int>
```

#### SHORT_VOWEL

Vowel of the syllable is short.

There are no required fields.

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### LONG_VOWEL

Vowel of the syllable is long.

There are no required fields.

Optional fields with their defaults:

```
syllablePosition: 0
syllablePositionAbsolute: false
```

#### MATCH

Phoneme sound value at these positions are all equal to each other.

Required fields:

```
positions: [{ syllable: <int>, sound: <int> }]
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### SOUND_ARRAY_MATCH

A certain string starting at the `initialPosition` value (inclusive) is the `array` value.

Required fields:

```
initialPosition: { syllable: <int>, sound: <int> }
array: [<phoneme strings>]
```

Optional fields with their defaults:

```
syllablePositionAbsolute: false
```

#### LENGTH

Number of syllables in a word is greater than, less than, or equal to the `length` value.

Required fields:

```
comparison: 'GREATER_THAN' / 'LESS_THAN' / 'EQUALS'
length: <int>
```

#### STRESSED

Syllable has stress of `order` value.

There are no required fields.

Optional fields with their defaults:

```
order: 1
syllablePosition: 0
syllablePositionAbsolute: false
```

#### BEFORE_STRESS

Syllable is located before the first syllable with the `order` value.

There are no required fields.

Optional fields with their defaults:

```
order: 1
```

#### STRESS_EXISTENCE

For each value in the `orders` list, the word has at least one syllable that takes stress of that order.

Required fields:

```
orders: [<int>]
```

#### STRESS_UNIQUENESS

For each value in the `orders` list, the word has no more than one syllable that takes stress of that order.

Required fields:

```
orders: [<int>]
```

#### STRESS_PARADIGM

For each position in the `positions` list, if the word satisfies its syllable condition with `value` as the assumed syllable index, then that syllable takes stress of `order` value.

Required fields:

```
order: <int>
positions: [{ value: <int>, condition: <syllable condition> }]
```

#### FOLLOWS_FROM_LAST_STEP

The previous evolution step passed its condition, and performed some nonzero alteration on the word.

This syllable condition is used to create an instantaneous composite transformation out of multiple atomic evolution steps.

The evolution step to which this syllable condition applies is performed `syllableShift` integer value of syllables away from the previous evolution step. 

Required fields:

```
syllableShift: <int>
```

Transformations
-

This is a data class that records evolution transformations that are performed on words as they are passed from parent languages to descendant languages.

Transformations have the following types.

#### SOUND_CHANGE

At each position in the `positions` list in the current syllable, in each object in the `changes` list, the `fromSound` value becomes the `toSound` value.

Required fields:

```
positions: [<int>]
changes: [{ fromSound: <phoneme string>, toSound: <phoneme string> }]
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SOUND_DELETION

At each position in the `positions` list in the current syllable, each sound in the `sounds` list becomes zero.

Required fields:

```
positions: [<int>]
sounds: [<phoneme string>]
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SOUND_INSERTION

At the `position` value in the current syllable, zero becomes the `sound` value. 

Required fields:

```
position: <int>
sound: <phoneme string>
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SOUND_MIGRATION

In each value of the `migrations` list, the phoneme in the `fromPosition` value is migrated into the syllable decided by the `syllableShift` value, and into the `toPosition` value.
The value in the original position is then deleted. 

If the target position is out of bounds, then the migration does not happen.

Required fields:

```
migrations: [{ fromPosition: <int>, syllableShift: <int>, toPosition: <int> }]
overwrite: <Boolean>
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SOUND_COPY

In each value of the `migrations` list, the phoneme in the `fromPosition` value is migrated into the syllable decided by the `syllableShift` value, and into the `toPosition` value.
The value in the original position is preserved.

If the target position is out of bounds, then the migration does not happen.

Required fields:

```
migrations: [{ fromPosition: <int>, syllableShift: <int>, toPosition: <int> }]
overwrite: <Boolean>
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SOUND_SWAP

Phonemes swap places.

Required fields:

```
swap: { fromPosition: <int>, syllableShift: <int>, toPosition: <int> }
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### CONSONANT_DEGEMINATION

If a consonant in the coda position of the given syllable is immediately followed by the same sound value in some initial position in the next syllable, the consonant becomes zero.

There are no required fields.

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SYLLABLE_COLLAPSE

One syllable length of positions is deleted from the word, starting at the `position` value within the syllable (inclusive), and the remaining syllables shifted up.

If position is not positive (i.e. not coda), and syllable is word-final, then the entire syllable is deleted.

Required fields:

```
position: <int>
reiterate: <Boolean>
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SYLLABLE_INSERTION

A new unstressed syllable of `phonemes` value is inserted somewhere in the word, before the current syllable position.

Required fields:

```
phonemes: [<phoneme string>]
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### STRESS_SHIFT

The stress of `order` value at the current syllable is moved `shift` value.

Required fields:

```
order: <int>
shift: <int>
```

Optional fields with their defaults:

```
condition: { type: DEFAULT }
```

#### SYLLABLE_POSITION_INSERTION

Insert a new syllable position at the specified position, and shift all farther positions away from the vowel core.

The position value cannot be 0. But it can be one unit past the current extremities.

This transformation cannot take a condition; it always applies categorically.

Required fields:

```
position: <int>
```

#### SYLLABLE_POSITION_DELETION

Delete the syllable position value at the specified position, including any value occupying that position.

The position value cannot be 0, and it must be confined to the current extremities.

This transformation cannot take a condition; it always applies categorically.

Required fields:

```
position: <int>
```