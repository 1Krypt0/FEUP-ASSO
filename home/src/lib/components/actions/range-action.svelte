<script lang="ts">
    import RangeSlider from "svelte-range-slider-pips";
	let theme = "default";
    export let value:number;
    export let max:number;
    export let min:number;
    export let step: number;
</script>

<section class:purple-theme={theme === "yellow"}>
    <RangeSlider values = {[value]} on:change={(evt)  => value = evt.detail.value} min={min} max={max} step={step} id="basic-slider"/>
    {value}
</section>