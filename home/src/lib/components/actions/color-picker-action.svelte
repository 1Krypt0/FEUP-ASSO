<script lang="ts">
    import ColorPicker from "svelte-awesome-color-picker";
    export let hex:string;
</script>

<section>
    <ColorPicker bind:hex isAlpha={false} canChangeMode={false}/>
</section>