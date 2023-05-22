<script lang="ts">
    import RangeSlider from "svelte-range-slider-pips";
	let theme = "default";
    export let intensity:number ;
</script>

<section class:purple-theme={theme === "yellow"}>
    <label for="basic-range">Range Label</label>
    <RangeSlider values = {[intensity]} on:change={(evt)  => intensity = evt.detail.value} id="basic-slider" />
    {intensity}
</section>